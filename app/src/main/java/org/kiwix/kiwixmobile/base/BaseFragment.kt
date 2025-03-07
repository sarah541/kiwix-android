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

package org.kiwix.kiwixmobile.base

import android.content.Context
import androidx.fragment.app.Fragment
import org.kiwix.kiwixmobile.KiwixApplication
import org.kiwix.kiwixmobile.di.components.ActivityComponent

/**
 * All fragments should inherit from this fragment.
 */

abstract class BaseFragment : Fragment() {

  override fun onAttach(context: Context?) {
    super.onAttach(context)
    inject(
      KiwixApplication.getApplicationComponent().activityComponent()
        .activity(activity!!)
        .build()
    )
  }

  abstract fun inject(activityComponent: ActivityComponent)
}
