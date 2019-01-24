package com.example.sakakoro.pushtestsaka

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.view.View


class ScreenTransitionAction {
//    private val dispatcher = Dispatcher
//
//    fun startActivity(activity: Activity, mode: ScreenMode, view: View?, usingAnimation: Boolean) {
//        var intent: Intent
//        when (mode) {
//            ScreenMode.areaList -> intent = Intent(activity, AreaListActivity::class.java)
//            ScreenMode.checkInDay -> intent = Intent(activity, CheckInDayActivity::class.java)
//            ScreenMode.numberOfPeople -> intent = Intent(activity, NumberOfPeopleActivity::class.java)
//            ScreenMode.budget -> intent = Intent(activity, BudgetActivity::class.java)
//            ScreenMode.hotelListOnSearchResult -> intent = Intent(activity, SearchResultActivity::class.java)
//            ScreenMode.infoOnHotelDetail -> intent = Intent(activity, HotelDetailActivity::class.java)
//            ScreenMode.planOnHotelDetail -> intent = Intent(activity, HotelDetailActivity::class.java)
//            ScreenMode.reviewOnHotelDetail -> intent = Intent(activity, HotelDetailActivity::class.java)
//            ScreenMode.keywordSearchOnHome -> intent = Intent(activity, KeywordSearchOnHomeActivity::class.java)
//            ScreenMode.registrationOnHome -> intent = Intent(activity, RegistrationOnHomeActivity::class.java)
//            ScreenMode.bookingOnHome -> intent = Intent(activity, BookingOnHomeActivity::class.java)
//            ScreenMode.planDetail -> intent = Intent(activity, PlanDetailActivity::class.java)
//            ScreenMode.afterCart -> intent = Intent(activity, AfterCartActivity::class.java)
//            else -> return
//        }
//        if (Judge.overLollipop() && usingAnimation) {
//            activity.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(activity, view, Constants.TransitionNameType.fromHome.value).toBundle())
//        } else {
//            activity.startActivity(intent)
//        }
//    }

//    fun changeScreen(screenMode: ScreenMode) = dispatcher.screenMode.dispatch(screenMode)

}

enum class ScreenMode {
    none,

    homeOnTop,
    searchHistoryOnTop,
    favoriteOnTop,
    cameraOnTop,
    mapOnMapOnTop,
    researchOnMapOnTop,

    areaList,
    areaListDetail,
    areaListRange,

    checkInDay,
    numberOfPeople,
    budget,

    keywordSearchOnHome,
    mapOnNearHotelOnHome,
    researchOnNearHotelOnHome,
    registrationOnHome,
    bookingOnHome,

    hotelListOnSearchResult,
    mapOnSearchResult,
    researchOnSearchResult,

    infoOnHotelDetail,
    bathOnHotelDetail,
    planOnHotelDetail,
    mapOnHotelDetail,
    reviewOnHotelDetail,

    planDetail,
    afterCart
}

