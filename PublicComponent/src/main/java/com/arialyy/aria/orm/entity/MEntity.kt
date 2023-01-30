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
import androidx.room.Index
import androidx.room.PrimaryKey
import com.arialyy.aria.core.DuaContext
import com.arialyy.aria.orm.DuaDb
import timber.log.Timber
import java.util.TimeZone

@Entity(indices = [Index(value = ["sourceUrl", "savePath"])])
data class MEntity(
  @PrimaryKey(autoGenerate = true) val mId: Int = 0,

  val keyId: Int = -1,

  /**
   * file source url
   */
  val sourceUrl: String,
  /**
   * file save path
   */
  val savePath: String,
  /**
   * extended Information
   */
  var ext: String? = null,

  /**
   * 当前peer的位置
   */
  val peerIndex: Int = 0,

  /**
   * peer总数
   */
  private var peerNum: Int = 0,

  /**
   * 是否是直播，true 直播
   */
  val isLive: Boolean = false,

  /**
   * 缓存目录
   */
  val cacheDir: String? = null

) {
  fun hasKey() = keyId != -1

  suspend fun getKeyInfo(): MKeyInfo? {
    if (!hasKey()) {
      Timber.w("no key info")
      return null
    }
    return DuaContext.getServiceManager().getDbService().getDuaDb()?.getMEntityDao()
      ?.getKeyInfoByKId(keyId)
  }
}