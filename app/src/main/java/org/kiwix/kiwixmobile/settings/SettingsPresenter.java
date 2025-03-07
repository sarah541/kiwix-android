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

package org.kiwix.kiwixmobile.settings;

import android.util.Log;
import io.reactivex.CompletableObserver;
import io.reactivex.disposables.Disposable;
import javax.inject.Inject;
import org.kiwix.kiwixmobile.base.BasePresenter;
import org.kiwix.kiwixmobile.data.DataSource;

class SettingsPresenter extends BasePresenter<SettingsContract.View>
  implements SettingsContract.Presenter {

  private final DataSource dataSource;

  @Inject SettingsPresenter(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Override
  public void clearHistory() {
    dataSource.clearHistory()
      .subscribe(new CompletableObserver() {
        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onComplete() {

        }

        @Override
        public void onError(Throwable e) {
          Log.e("SettingsPresenter", e.toString());
        }
      });
  }
}
