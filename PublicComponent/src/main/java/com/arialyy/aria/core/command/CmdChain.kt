/*
 * Copyright (C) 2016 AriaLyy(https://github.com/AriaLyy/Aria)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.arialyy.aria.core.command

import com.arialyy.aria.core.inf.ITaskQueue
import com.arialyy.aria.core.task.ITask

/**
 * @Author laoyuyu
 * @Description
 * @Date 11:06 AM 2023/1/27
 **/
internal class CmdChain(
  private val interceptors: List<ICmdInterceptor>,
  private val index: Int = 0,
  private val task: ITask,
  private val queue: ITaskQueue<ITask>
) : ICmdInterceptor.IChain {
  override fun getQueue(): ITaskQueue<ITask> {
    return queue
  }

  override fun getTask(): ITask {
    return task
  }

  override fun proceed(task: ITask): CmdResp {
    val next = CmdChain(interceptors, index, task, queue)
    val interceptor = interceptors[index]
    return interceptor.interceptor(next)
  }
}