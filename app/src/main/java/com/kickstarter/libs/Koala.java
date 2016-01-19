package com.kickstarter.libs;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.kickstarter.libs.utils.KoalaUtils;
import com.kickstarter.models.Activity;
import com.kickstarter.models.Comment;
import com.kickstarter.models.Project;
import com.kickstarter.services.DiscoveryParams;

import java.util.HashMap;
import java.util.Map;

public final class Koala {
  private final @NonNull TrackingClientType client;

  public Koala(final @NonNull TrackingClientType client) {
    this.client = client;
  }

  // APPLICATION LIFECYCLE
  public void trackAppOpen() {
    client.track("App Open");
  }

  public void trackAppClose() {
    client.track("App Close");
  }

  public void trackMemoryWarning() {
    client.track("App Memory Warning");
  }

  // DISCOVERY
  public void trackDiscovery(final @NonNull DiscoveryParams params, final boolean isOnboardingVisible) {
    final Map<String, Object> props = KoalaUtils.discoveryParamsProperties(params);
    props.put("discover_onboarding_is_visible", isOnboardingVisible);
    client.track("Discover List View", props);
  }

  public void trackDiscoveryFilters() {
    client.track("Discover Switch Modal", new HashMap<String, Object>() {{
      put("modal_type", "filters");
    }});
  }

  public void trackDiscoveryFilterSelected(final @NonNull DiscoveryParams params) {
    client.track("Discover Modal Selected Filter", KoalaUtils.discoveryParamsProperties(params));
  }

  /**
   * Tracks a project show event.
   * @param intentRefTag (nullable) The ref tag present in the activity upon displaying the project.
   * @param cookieRefTag (nullable) The ref tag extracted from the cookie store upon viewing the project.
   */
  public void trackProjectShow(final @NonNull Project project, final @Nullable RefTag intentRefTag, final @Nullable RefTag cookieRefTag) {

    final Map<String, Object> properties = KoalaUtils.projectProperties(project);

    if (intentRefTag != null) {
      properties.put("ref_tag", intentRefTag.tag());
    }

    if (cookieRefTag != null) {
      properties.put("referrer_credit", cookieRefTag.tag());
    }

    client.track("Project Page", properties);
  }

  // PROJECT STAR
  public void trackProjectStar(final @NonNull Project project) {
    if (project.isStarred()) {
      client.track("Project Star");
    } else {
      client.track("Project Unstar");
    }
  }

  // COMMENTING
  public void trackProjectCommentCreate(final @NonNull Project project, final @NonNull Comment comment) {
    client.track("Project Comment Create", KoalaUtils.projectProperties(project));
  }

  public void trackProjectCommentsView(final @NonNull Project project) {
    client.track("Project Comment View", KoalaUtils.projectProperties(project));
  }

  public void trackProjectCommentLoadMore(final @NonNull Project project) {
    client.track("Project Comment Load Older", KoalaUtils.projectProperties(project));
  }

  // ACTIVITY
  public void trackActivityView(final int pageCount) {
    if (pageCount == 0) {
      client.track("Activity View");
    } else {
      client.track("Activity Load More", new HashMap<String, Object>() {{
        put("page_count", pageCount);
      }});
    }
  }

  // SEARCH
  public void trackSearchView() {
    client.track("Discover Search");
  }

  public void trackSearchResults(final @NonNull String query, final int pageCount) {
    if (pageCount == 1) {
      client.track("Discover Search Results", new HashMap<String, Object>() {{
        put("search_term", query);
      }});
    } else {
      client.track("Discover Search Results Load More", new HashMap<String, Object>() {{
        put("search_term", query);
        put("page_count", pageCount);
      }});
    }
  }

  public void trackActivityTapped(final @NonNull Activity activity) {
    client.track("Activity View Item", KoalaUtils.activityProperties(activity));
  }

  // SESSION EVENTS
  public void trackLoginRegisterTout(@Nullable final String intent) {
    client.track("Application Login or Signup", new HashMap<String, Object>() {{
      put("intent", intent);
    }});
  }

  public void trackLoginSuccess() {
    client.track("Login");
  }

  public void trackLoginError() {
    client.track("Errored User Login");
  }

  public void trackTwoFactorAuthView() {
    client.track("Two-factor Authentication Confirm View");
  }

  public void trackTwoFactorResendCode() {
    client.track("Two-factor Authentication Resend Code");
  }

  public void trackRegisterFormView() {
    client.track("User Signup");
  }

  public void trackRegisterError() {
    client.track("Errored User Signup");
  }

  public void trackRegisterSuccess() {
    client.track("New User");
  }

  public void trackResetPasswordFormView() {
    client.track("Forgot Password View");
  }

  public void trackResetPasswordSuccess() {
    client.track("Forgot Password Requested");
  }

  public void trackResetPasswordError() {
    client.track("Forgot Password Errored");
  }

  public void trackFacebookConfirmation() {
    client.track("Facebook Confirm");
  }

  public void trackFacebookLoginSuccess() {
    client.track("Facebook Login");
  }

  public void trackFacebookLoginError() {
    client.track("Errored Facebook Login");
  }

  public void trackLogout() {
    client.track("Logout");
  }

  public void trackSignupNewsletterToggle(final boolean sendNewsletters) {
    client.track("Signup Newsletter Toggle", new HashMap<String, Object>() {{
      put("send_newsletters", sendNewsletters);
    }});
  }

  // SETTINGS
   public void trackContactEmailClicked() {
    client.track("Contact Email Clicked");
  }

  public void trackNewsletterToggle(final boolean sendNewsletter) {
    if (sendNewsletter) {
      client.track("Newsletter Subscribe");
    } else {
     client.track("Newsletter Unsubscribe");
    }
  }

  public void trackSettingsView() {
    client.track("Settings View");
  }

  // CHECKOUT
  public void trackCheckoutNext() { // rewards webview and top nav
    client.track("Checkout Next");
  }
  public void trackCheckoutCancel() {
    client.track("Checkout Cancel");
  }

  public void trackCheckoutLoadFailed() {
    // TODO: set up error props
  }

  public void trackCheckoutShowShareSheet() {
    client.track("Checkout Show Share Sheet");
  }

  public void trackCheckoutCancelShareSheet() {
    client.track("Checkout Cancel Share Sheet");
  }

  public void trackCheckoutShowFacebookShareView() {
    client.track("Checkout Show Share", new HashMap<String, Object>() {{
      put("share_type", "facebook");
    }});
  }

  public void trackCheckoutShowTwitterShareView() {
    client.track("Checkout Show Share", new HashMap<String, Object>() {{
      put("share_type", "twitter");
    }});
  }

  public void trackCheckoutShareFinished() {
    client.track("Checkout Share Finished");
  }

  public void trackCheckoutFinishJumpToDiscovery() {
    client.track("Checkout Finished Discover More");
  }

  public void trackCheckoutFinishJumpToProject() {
    client.track("Checkout Finished Discover Open Project");
  }

  // SHARE
  public void trackShowProjectShareSheet() {
    client.track("Project Show Share Sheet");
  }

  public void trackCancelProjectShareSheet() {
    client.track("Project Cancel Share Sheet");
  }

  public void trackShowProjectFacebookShareView() {
    client.track("Project Show Share", new HashMap<String, Object>() {{
      put("share_type", "facebook");
    }});
  }

  public void trackShowProjectTwitterShareView() {
    client.track("Project Show Share", new HashMap<String, Object>() {{
      put("share_type", "twitter");
    }});
  }

  public void trackProjectFacebookShare() {
    client.track("Project Share", new HashMap<String, Object>() {{
      put("share_type", "facebook");
    }});
  }

  public void trackProjectTwitterShare() {
    client.track("Project Share", new HashMap<String, Object>() {{
      put("share_type", "twitter");
    }});
  }

  // PROFILE
  public void trackProfileView() {
    client.track("Profile View My");
  }

  // RATING
  public void trackAppRatingNow() {
    client.track("Checkout Finished Alert App Store Rating Rate Now");
  }

  public void trackAppRatingRemindLater() {
    client.track("Checkout Finished Alert App Store Rating Remind Later");
  }

  public void trackAppRatingNoThanks() {
    client.track("Checkout Finished Alert App Store Rating No Thanks");
  }

  // VIDEO
  // todo: add properties
  public void trackVideoStart(final @NonNull Project project) {
    client.track("Project Video Start", KoalaUtils.projectProperties(project));
  }

  public void trackVideoEndedScrubbing(final @NonNull Project project) {
    client.track("Project Video End Scrubbing", KoalaUtils.projectProperties(project));
  }

  public void trackVideoPaused(final @NonNull Project project) {
    client.track("Project Video Pause", KoalaUtils.projectProperties(project));
  }

  public void trackVideoResume(final @NonNull Project project) {
    client.track("Project Video Resume", KoalaUtils.projectProperties(project));
  }

  public void trackVideoStop(final @NonNull Project project) {
    client.track("Project Video Stop", KoalaUtils.projectProperties(project));
  }

  public void trackVideoCompleted(final @NonNull Project project) {
    client.track("Project Video Complete", KoalaUtils.projectProperties(project));
  }
}
