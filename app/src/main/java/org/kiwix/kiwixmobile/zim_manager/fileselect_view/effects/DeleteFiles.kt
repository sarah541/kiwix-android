/*
 * Kiwix Android
 * Copyright (c) 2019 Kiwix <android.kiwix.org>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 *
 */

package org.kiwix.kiwixmobile.zim_manager.fileselect_view.effects

import android.app.Activity
import org.kiwix.kiwixmobile.R.string
import org.kiwix.kiwixmobile.database.newdb.dao.NewBookDao
import org.kiwix.kiwixmobile.extensions.toast
import org.kiwix.kiwixmobile.utils.DialogShower
import org.kiwix.kiwixmobile.utils.KiwixDialog.DeleteZim
import org.kiwix.kiwixmobile.utils.files.FileUtils
import org.kiwix.kiwixmobile.zim_manager.ZimReaderContainer
import org.kiwix.kiwixmobile.zim_manager.fileselect_view.adapter.BooksOnDiskListItem.BookOnDisk
import javax.inject.Inject

class DeleteFiles(private val booksOnDiskListItem: List<BookOnDisk>) :
  SideEffect<Unit> {

  @Inject lateinit var dialogShower: DialogShower
  @Inject lateinit var newBookDao: NewBookDao
  @Inject lateinit var zimReaderContainer: ZimReaderContainer

  override fun invokeWith(activity: Activity) {
    activityComponent(activity).inject(this)
    booksOnDiskListItem.forEach {
      dialogShower.show(DeleteZim(it), {
        if (deleteSpecificZimFile(it)) {
          if (it.file.canonicalPath == zimReaderContainer.zimCanonicalPath) {
            zimReaderContainer.setZimFile(null)
          }
          activity.toast(string.delete_specific_zim_toast)
        } else {
          activity.toast(string.delete_zim_failed)
        }
      })
    }
  }

  private fun deleteSpecificZimFile(book: BookOnDisk): Boolean {
    val file = book.file
    FileUtils.deleteZimFile(file.path)
    if (file.exists()) {
      return false
    }
    newBookDao.delete(book.databaseId!!)
    return true
  }
}
