/*Name : TweeCool
 *version: 1.9 
 *Description: Get the latest tweets from twitter.
 *Website: www.tweecool.com
 *Licence: No licence, feel free to do whatever you want.
 *Author: TweeCool
 */
!function (t) { t.fn.extend({ tweecool: function (e) { function a(t) { var e = new Date, a = Date.parse(e), r = 1e3 * t, i = (a - r) / 1e3, o = 1, n = 60, s = 3600, _ = 86400, l = 604800, c = 2592e3, u = 31536e3; return i > o && n > i ? Math.round(i / o) + " second" + (Math.round(i / o) > 1 ? "s" : "") + " ago" : i >= n && s > i ? Math.round(i / n) + " minute" + (Math.round(i / n) > 1 ? "s" : "") + " ago" : i >= s && _ > i ? Math.round(i / s) + " hour" + (Math.round(i / s) > 1 ? "s" : "") + " ago" : i >= _ && l > i ? Math.round(i / _) + " day" + (Math.round(i / _) > 1 ? "s" : "") + " ago" : i >= l && c > i ? Math.round(i / l) + " week" + (Math.round(i / l) > 1 ? "s" : "") + " ago" : i >= c && u > i ? Math.round(i / c) + " month" + (Math.round(i / c) > 1 ? "s" : "") + " ago" : "over a year ago" } var r = { username: "tweecool", limit: 5, profile_image: !0, show_time: !0, show_media: !1, show_media_size: "thumb", show_actions: !1, action_reply_icon: "&crarr;", action_retweet_icon: "&prop;", action_favorite_icon: "&#10084", profile_img_url: "profile", show_retweeted_text: !1 }, e = t.extend(r, e); return this.each(function () { var r, i, o, n, s, _ = e, l = t(this), c = t("<ul class='slides'>").appendTo(l), u = /(\b(https?|ftp|file):\/\/[-A-Z0-9+&@#\/%?=~_|!:,.;]*[-A-Z0-9+&@#\/%=~_|])/gi, h = /@+(\w+)/gi, w = /#+(\w+)/gi; t.getJSON("http://www.framework-y.com/tweecool/index.php/api/" + _.username + "/" + _.limit, function (e) { return e.errors || null == e ? (l.html("No tweets available."), !1) : void t.each(e.tweets, function (t, l) { r = _.profile_image ? "tweet" == _.profile_img_url ? '<a href="https://twitter.com/' + _.username + "/status/" + l.id_str + '" target="_blank"><img src="' + e.user.profile_image_url + '" alt="' + _.username + '" /></a>' : '<a href="https://twitter.com/' + _.username + '" target="_blank"><img src="' + e.user.profile_image_url + '" alt="' + _.username + '" /></a>' : "", o = _.show_time ? a(l.timestamp) : "", i = _.show_media && l.media_url ? '<a href="https://twitter.com/' + _.username + "/status/" + l.id_str + '" target="_blank"><img src="' + l.media_url + ":" + _.show_media_size + '" alt="' + _.username + '" class="media" /></a>' : "", _.show_actions ? (n = '<div class="action-box"><ul>', n += '<li class="ab_reply"><a title="Reply" href="https://twitter.com/intent/tweet?in_reply_to=' + l.id_str + '">' + _.action_reply_icon + "</a></li>", n += '<li class="ab_retweet"><a title="Retweet" href="https://twitter.com/intent/retweet?tweet_id=' + l.id_str + '">' + _.action_retweet_icon + "</a>" + ("" != l.retweet_count_f ? "<span>" + l.retweet_count_f + "</span>" : "") + "</li>", n += '<li class="ab_favorite"><a title="Favorite" href="https://twitter.com/intent/favorite?tweet_id=' + l.id_str + '">' + _.action_favorite_icon + "</a>" + ("" != l.favorite_count_f ? "<span>" + l.favorite_count_f + "</span>" : "") + "</li>", n += "</ul></div>") : n = "", s = _.show_retweeted_text && l.retweeted_text ? l.retweeted_text : l.text, c.append("<li>" + r + '<div class="tweets_txt">' + s.replace(u, '<a href="$1" target="_blank">$1</a>').replace(h, '<a href="https://twitter.com/$1" target="_blank">@$1</a>').replace(w, '<a href="https://twitter.com/search?q=%23$1" target="_blank">#$1</a>') + i + " <span>" + o + "</span>" + n + "</div></li>") }) }).fail(function (t, e, a) { l.html("No tweets available") }) }) } }) }(jQuery);


/*
 * Facebook Wall
 * https://github.com/thomasclausen/jquery-facebook-wall
 * Under MIT License
 */
(function (n) {
    n.fn.facebook_wall = function (e) { function t(e) { return s(a(i(e))) } function s(e) { return e.replace(/(\r\n)|(\n\r)|\r|\n/g, "<br />") } function a(e) { return e.replace(/((http|https|ftp):\/\/[\w?=&.\/-;#~%-]+(?![\w\s?&.\/;#~%"=-]*>))/g, '<a href="$1" target="_blank">$1</a>') } function i(e) { return e.replace(/</g, "&lt;").replace(/>/g, "&gt;") } function o(t) { var s = new Date(1e3 * t), a = (s.toGMTString(), Math.round((new Date).getTime() / 1e3) - t); return 10 > a ? " seconds ago" : 60 > a ? Math.round(a) + " seconds ago" : 1 === Math.round(a / 60) ? Math.round(a / 60) + " minuts ago" : Math.round(a / 60) < 60 ? Math.round(a / 60) + " minuts ago" : 1 === Math.round(a / 3600) ? Math.round(a / 3600) + " hour ago" : Math.round(a / 3600) < 24 ? Math.round(a / 3600) + " hours ago" : 1 === Math.round(a / 86400) ? Math.round(a / 86400) + " day ago" : Math.round(a / 86400) <= 10 ? Math.round(a / 86400) + " days ago" : e.text_labels.days[s.getDay()] + " " + s.getDate() + ". " + e.text_labels.months[s.getMonth()] + " " + s.getFullYear() } if (void 0 !== e.id && void 0 !== e.access_token) { e = n.extend({ id: "", access_token: "", limit: 15, timeout: 400, speed: 400, effect: "slide", locale: "en_US", avatar_size: "square", message_length: 200, show_guest_entries: !0, text_labels: { shares: { singular: "Shared % time", plural: "Shared % times" }, likes: { singular: "% Like", plural: "% Likes" }, comments: { singular: "% comment", plural: "% comments" }, like: "Like", comment: "Write comment", share: "Share", days: ["Sunday", "Monday", "Thuesday", "Wednesday", "Thursday", "Friday", "Saturday"], months: ["januari", "february", "march", "april", "may", "june", "july", "august", "september", "october", "november", "december"] }, on_complete: null }, e); var l = "https://graph.facebook.com/", r = e.show_guest_entries === !1 ? "posts" : "feed", d = l + e.id + "/" + r + "/?access_token=" + e.access_token + "&limit=" + e.limit + "&locale=" + e.locale + "&date_format=U&callback=?", c = n(this); c.append('<div class="facebook-loading"></div>'), n.getJSON(d, function (s) { n.each(s.data, function () { var s, a = "", i = "", n = "", r = ""; if (this.is_hidden === !1) { if ("link" === this.type ? i = "type-link " : "photo" === this.type ? i = "type-photo " : "status" === this.type ? i = "type-status " : "video" === this.type && (i = "type-video "), a += '<li class="post ' + i + "avatar-size-" + e.avatar_size + '">', a += '<div class="meta-header">', a += '<div class="avatar"><a href="http://www.facebook.com/profile.php?id=' + this.from.id + '" target="_blank" title="' + this.from.name + '"><img src="' + (l + this.from.id + "/picture?type=" + e.avatar_size) + '" alt="' + this.from.name + '" /></a></div>', a += '<div class="author"><a href="http://www.facebook.com/profile.php?id=' + this.from.id + '" target="_blank" title="' + this.from.name + '">' + this.from.name + "</a></div>", a += '<div class="date">' + o(this.created_time) + "</div>", a += "</div>", void 0 !== this.message ? a += e.message_length > 0 && this.message.length > e.message_length ? '<div class="message">' + t(this.message.substring(0, e.message_length)) + "...</div>" : '<div class="message">' + t(this.message) + "</div>" : void 0 !== this.story && (a += e.message_length > 0 && this.story.length > e.message_length ? '<div class="story">' + t(this.story.substring(0, e.message_length)) + "...</div>" : '<div class="story">' + t(this.story) + "</div>"), ("link" === this.type || "photo" === this.type || "video" === this.type) && (n = void 0 !== this.picture || void 0 !== this.object_id ? " border-left" : "", a += '<div class="media' + n + ' clearfix">', void 0 !== this.picture ? a += '<div class="image"><a href="' + this.link + '" target="_blank"><img src="' + this.picture + '" /></a></div>' : void 0 !== this.object_id && (a += '<div class="image"><a href="' + this.link + '" target="_blank"><img src="' + (l + this.object_id + "/picture?type=album") + '" /></a></div>'), a += '<div class="media-meta">', void 0 !== this.name && (a += '<div class="name"><a href="' + this.link + '" target="_blank">' + this.name + "</a></div>"), void 0 !== this.caption && (a += '<div class="caption">' + t(this.caption) + "</div>"), void 0 !== this.description && (a += '<div class="description">' + t(this.description) + "</div>"), a += "</div>", a += "</div>"), a += '<div class="meta-footer">', a += '<time class="date" datetime="' + this.created_time + '" pubdate>' + o(this.created_time) + "</time>", void 0 !== this.likes && void 0 !== this.likes.data && (a += void 0 !== this.likes.count ? 1 === this.likes.count ? '<span class="seperator">&middot;</span><span class="likes">' + e.text_labels.likes.singular.replace("%", this.likes.count) + "</span>" : '<span class="seperator">&middot;</span><span class="likes">' + e.text_labels.likes.plural.replace("%", this.likes.count) + "</span>" : 1 === this.likes.data.length ? '<span class="seperator">&middot;</span><span class="likes">' + e.text_labels.likes.singular.replace("%", this.likes.data.length) + "</span>" : '<span class="seperator">&middot;</span><span class="likes">' + e.text_labels.likes.plural.replace("%", this.likes.data.length) + "</span>"), void 0 !== this.comments && void 0 !== this.comments.data && (a += 1 === this.comments.data.length ? '<span class="seperator">&middot;</span><span class="comments">' + e.text_labels.comments.singular.replace("%", this.comments.data.length) + "</span>" : '<span class="seperator">&middot;</span><span class="comments">' + e.text_labels.comments.plural.replace("%", this.comments.data.length) + "</span>"), a += void 0 !== this.shares ? 1 === this.shares.count ? '<span class="seperator">&middot;</span><span class="shares">' + e.text_labels.shares.singular.replace("%", this.shares.count) + "</span>" : '<span class="seperator">&middot;</span><span class="shares">' + e.text_labels.shares.plural.replace("%", this.shares.count) + "</span>" : '<span class="seperator">&middot;</span><span class="shares">' + e.text_labels.shares.plural.replace("%", "0") + "</span>", r = this.id.split("_"), a += '<div class="actionlinks"><span class="like"><a href="http://www.facebook.com/permalink.php?story_fbid=' + r[1] + "&id=" + r[0] + '" target="_blank">' + e.text_labels.like + '</a></span><span class="seperator">&middot;</span><span class="comment"><a href="http://www.facebook.com/permalink.php?story_fbid=' + r[1] + "&id=" + r[0] + '" target="_blank">' + e.text_labels.comment + '</a></span><span class="seperator">&middot;</span><span class="share"><a href="http://www.facebook.com/permalink.php?story_fbid=' + r[1] + "&id=" + r[0] + '" target="_blank">' + e.text_labels.share + "</a></span></div>", a += "</div>", void 0 !== this.likes && void 0 !== this.likes.data, void 0 !== this.comments && void 0 !== this.comments.data) { for (a += '<ul class="comment-list">', s = 0; s < this.comments.data.length; s++) a += '<li class="comment">', a += '<div class="meta-header">', a += '<div class="avatar"><a href="http://www.facebook.com/profile.php?id=' + this.comments.data[s].from.id + '" target="_blank" title="' + this.comments.data[s].from.name + '"><img src="' + (l + this.comments.data[s].from.id + "/picture?type=" + e.avatar_size) + '" alt="' + this.comments.data[s].from.name + '" /></a></div>', a += '<div class="author"><a href="http://www.facebook.com/profile.php?id=' + this.comments.data[s].from.id + '" target="_blank" title="' + this.comments.data[s].from.name + '">' + this.comments.data[s].from.name + "</a></div>", a += '<time class="date" datetime="' + this.comments.data[s].created_time + '" pubdate>' + o(this.comments.data[s].created_time) + "</time>", a += "</div>", a += '<div class="message">' + t(this.comments.data[s].message) + "</div>", a += '<time class="date" datetime="' + this.comments.data[s].created_time + '" pubdate>' + o(this.comments.data[s].created_time) + "</time>", a += "</li>"; a += "</ul>" } a += "</li>", c.append(a) } }) }).done(function () { n(".facebook-loading", c).fadeOut(800, function () { n(this).remove(); for (var t = 0; t < c.children("li").length; t++) "none" === e.effect ? c.children("li").eq(t).show() : "fade" === e.effect ? c.children("li").eq(t).delay(t * e.timeout).fadeIn(e.speed) : c.children("li").eq(t).delay(t * e.timeout).slideDown(e.speed, function () { n(this).css("overflow", "visible") }) }), n.isFunction(e.on_complete) && e.on_complete.call() }) } };
})(jQuery);


/*
* ===========================================================
* SOCIAL STREAM - FRAMEWORK Y
* ===========================================================
* Social stream of Facebook and Twitter, this script have 4 display types: simple list, scroll box container, slider and carousel
* The script require Flexslider for slider and carousel display type and Scroll box for scroll box display type
* Documentation: www.framework-y.com/components/social.html
* 
* Pixor - Copyright (c) Federico Schiocchet - Pixor - Framework Y
*/

var facebook_token = "821948481218796|87fGsmQYzi0SBmJBp2bUv4dXTjM";
(function ($) {
    $(document).ready(function () {
        $("*[data-social-id].social-feed-fb").each(function () {
            var t = this;
            var count = 4;
            var optionsString = $(t).attr("data-options");
            var id = $(t).attr("data-social-id");
            var token = $(t).attr("data-token");
            if (isEmpty(token)) token = facebook_token;
            var optionsArr;
            var options = {
                access_token: token,
                limit: count,
                locale: "en_US",
                show_guest_entries: false
            }

            if (!isEmpty(optionsString)) {
                optionsArr = optionsString.split(",");
                options = getOptionsString(optionsString, options);
            }

            if (!isEmpty(id)) options["id"] = id;
            $(t).facebook_wall(options);

            if ($(t).hasClass("flexslider")) {
                var timerVar;
                timerVar = self.setInterval(function () {
                    if ($(t).find('li.post').length == options["limit"] && $(t).find('.facebook-loading').length == 0) {
                        $(t).html("<ul class='slides'>" + $(t).html() + "</ul>");


                        $(t).find("li").each(function (index) {
                            $(this).html("<div class='fb-container'>" + $(this).html() + '</div>');

                        });

                        if (isEmpty($(t).attr("data-trigger"))) $(t).initFlexSlider();
                        $(t).css("opacity", 1);
                        clearInterval(timerVar);
                    }
                }, 1000);
            }
        });

        $("*[data-social-id].social-feed-tw").each(function () {
            var t = this;
            var optionsString = $(t).attr("data-options");
            var id = $(t).attr("data-social-id");
            var optionsArr;
            var options = {
                limit: 4,
                show_media: true
            }

            if (!isEmpty(optionsString)) {
                optionsArr = optionsString.split(",");
                options = getOptionsString(optionsString, options);
            }

            if (!isEmpty(id)) options["username"] = id;
            $(t).tweecool(options);

            if ($(t).hasClass("flexslider")) {
                var timerVar;
                timerVar = self.setInterval(function () {
                    if ($(t).find('ul li').length) {
                        if (isEmpty($(t).attr("data-trigger"))) $(t).initFlexSlider();
                        clearInterval(timerVar);
                    }
                }, 1300);
            }
        });
    });
}(jQuery));


