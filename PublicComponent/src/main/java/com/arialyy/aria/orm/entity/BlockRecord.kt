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
package com.arialyy.aria.orm.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey

/**
 * @Author laoyuyu
 * @Description
 * @Date 10:16 AM 2023/1/19
 **/
@Entity(
  foreignKeys = [ForeignKey(
    entity = TaskRecord::class,
    parentColumns = ["tId"],
    childColumns = ["tId"],
    onDelete = CASCADE
  )]
)
data class BlockRecord(
  @PrimaryKey(autoGenerate = true) val bId: Int = 0,

  val tId: Int,

  /**
   * 开始位置
   */
  val startLocation: Long = 0,

  /**
   * 结束位置
   */
  val endLocation: Long = 0,

  val blockSize: Long = 0,

  val isComplete: Boolean = false
)