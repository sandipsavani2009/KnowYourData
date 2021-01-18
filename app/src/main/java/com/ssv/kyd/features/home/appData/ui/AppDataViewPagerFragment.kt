package com.ssv.kyd.features.home.appData.ui

import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.ssv.kyd.R
import com.ssv.kyd.features.helper.DataProvider
import kotlinx.android.synthetic.main.fragment_app_data_view_pager.*

class AppDataViewPagerFragment(val position: Int = 0, val dataID: Int = 0): Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_app_data_view_pager, container, false)
    }

    private fun initViews() {
        permission_request_btn.setOnClickListener { askForPermission() }
    }

    private fun askForPermission() {
        activity?.let {
            val permission = DataProvider.getPermissionTypeFromDataId(dataID)
            ActivityCompat.requestPermissions(activity!!, arrayOf(permission), dataID)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == dataID &&
            grantResults.isNotEmpty() &&
            grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            val data = DataProvider.getData(dataID)
            if (data?.isNotEmpty() == true) {
                showData(data)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        checkForDataAvailability()

    }

    private fun checkForDataAvailability() {
        if (DataProvider.isDataRestricted(dataID)) {
            val permission = DataProvider.getPermissionTypeFromDataId(dataID)

            if (ContextCompat.checkSelfPermission(context!!, permission) != PackageManager.PERMISSION_GRANTED) {
                data_available_layout.visibility = View.GONE
                data_not_available_layout.visibility = View.VISIBLE

            } else showData(DataProvider.getData(dataID))
        } else showData(DataProvider.getData(dataID))
    }

    private fun showData(data: String?) {
        data_not_available_layout.visibility = View.GONE
        data_available_layout.visibility = View.VISIBLE

        data_value_tv.text = data
    }
}