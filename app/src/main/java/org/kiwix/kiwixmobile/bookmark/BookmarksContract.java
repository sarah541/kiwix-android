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

package org.kiwix.kiwixmobile.bookmark;

import java.util.List;
import org.kiwix.kiwixmobile.base.BaseContract;

interface BookmarksContract {
  interface View extends BaseContract.View<Presenter> {
    void updateBookmarksList(List<BookmarkItem> bookmarks);

    void notifyBookmarksListFiltered(List<BookmarkItem> bookmarks);
  }

  interface Presenter extends BaseContract.Presenter<View> {
    void loadBookmarks(boolean showBookmarksCurrentBook);

    void filterBookmarks(List<BookmarkItem> bookmarksList, String newText);

    void deleteBookmarks(List<BookmarkItem> deleteList);
  }
}
