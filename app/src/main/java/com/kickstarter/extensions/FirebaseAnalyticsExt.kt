package com.kickstarter.extensions

import android.content.Context
import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics

fun firebaseCustomEvent(context: Context, eventName: String) {
    val bundle = Bundle()
    FirebaseAnalytics.getInstance(context).logEvent(eventName, bundle)
}

fun firebaseCustomEventWithParams(context: Context, eventName: String, key: String, eventAttribute: String) {
    val bundle = Bundle()
    bundle.putString(key, eventAttribute)
    FirebaseAnalytics.getInstance(context).logEvent(eventName, bundle)
}