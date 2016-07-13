package com.it5.okhttp_demo.been;

import java.util.List;

/**
 * Created by IT5 on 2016/7/13.
 */
public class GitUser {

    /**
     * url : https://api.github.com/repos/square/okhttp/issues/2720
     * repository_url : https://api.github.com/repos/square/okhttp
     * labels_url : https://api.github.com/repos/square/okhttp/issues/2720/labels{/name}
     * comments_url : https://api.github.com/repos/square/okhttp/issues/2720/comments
     * events_url : https://api.github.com/repos/square/okhttp/issues/2720/events
     * html_url : https://github.com/square/okhttp/issues/2720
     * id : 164965097
     * number : 2720
     * title : When use OkHttp3 in Multithreading program, sometimes "Too many open files" exception occurred
     * user : {"login":"jiechu","id":3069875,"avatar_url":"https://avatars.githubusercontent.com/u/3069875?v=3","gravatar_id":"","url":"https://api.github.com/users/jiechu","html_url":"https://github.com/jiechu","followers_url":"https://api.github.com/users/jiechu/followers","following_url":"https://api.github.com/users/jiechu/following{/other_user}","gists_url":"https://api.github.com/users/jiechu/gists{/gist_id}","starred_url":"https://api.github.com/users/jiechu/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/jiechu/subscriptions","organizations_url":"https://api.github.com/users/jiechu/orgs","repos_url":"https://api.github.com/users/jiechu/repos","events_url":"https://api.github.com/users/jiechu/events{/privacy}","received_events_url":"https://api.github.com/users/jiechu/received_events","type":"User","site_admin":false}
     * labels : []
     * state : open
     * locked : false
     * assignee : null
     * assignees : []
     * milestone : null
     * comments : 1
     * created_at : 2016-07-11T23:51:52Z
     * updated_at : 2016-07-12T01:49:41Z
     * closed_at : null
     * body : Read the introduction, OkHttp3 has a connection pool built-in. It is better than Spring Rest Template. My application has a ThreadPoolExecutor with 1000 thread size. It works on Linux server. It seems OkHttp3 cannot work well with the ThreadPoolExecutor since sometimes I see "Too many open files" in the log. When I turned back to Spring Rest Template, everything is well.
     Is there any thing I missed in the OkHttp3 usage? Or OkHttp3 is preferred to android.
     Note: This time OkHttp3 is used in Java program, not Android apk.
     */

    private String url;
    private String repository_url;
    private String labels_url;
    private String comments_url;
    private String events_url;
    private String html_url;
    private int id;
    private int number;
    private String title;
    /**
     * login : jiechu
     * id : 3069875
     * avatar_url : https://avatars.githubusercontent.com/u/3069875?v=3
     * gravatar_id :
     * url : https://api.github.com/users/jiechu
     * html_url : https://github.com/jiechu
     * followers_url : https://api.github.com/users/jiechu/followers
     * following_url : https://api.github.com/users/jiechu/following{/other_user}
     * gists_url : https://api.github.com/users/jiechu/gists{/gist_id}
     * starred_url : https://api.github.com/users/jiechu/starred{/owner}{/repo}
     * subscriptions_url : https://api.github.com/users/jiechu/subscriptions
     * organizations_url : https://api.github.com/users/jiechu/orgs
     * repos_url : https://api.github.com/users/jiechu/repos
     * events_url : https://api.github.com/users/jiechu/events{/privacy}
     * received_events_url : https://api.github.com/users/jiechu/received_events
     * type : User
     * site_admin : false
     */

    private UserBean user;
    private String state;
    private boolean locked;
    private Object assignee;
    private Object milestone;
    private int comments;
    private String created_at;
    private String updated_at;
    private Object closed_at;
    private String body;
    private List<?> labels;
    private List<?> assignees;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRepository_url() {
        return repository_url;
    }

    public void setRepository_url(String repository_url) {
        this.repository_url = repository_url;
    }

    public String getLabels_url() {
        return labels_url;
    }

    public void setLabels_url(String labels_url) {
        this.labels_url = labels_url;
    }

    public String getComments_url() {
        return comments_url;
    }

    public void setComments_url(String comments_url) {
        this.comments_url = comments_url;
    }

    public String getEvents_url() {
        return events_url;
    }

    public void setEvents_url(String events_url) {
        this.events_url = events_url;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public Object getAssignee() {
        return assignee;
    }

    public void setAssignee(Object assignee) {
        this.assignee = assignee;
    }

    public Object getMilestone() {
        return milestone;
    }

    public void setMilestone(Object milestone) {
        this.milestone = milestone;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public Object getClosed_at() {
        return closed_at;
    }

    public void setClosed_at(Object closed_at) {
        this.closed_at = closed_at;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<?> getLabels() {
        return labels;
    }

    public void setLabels(List<?> labels) {
        this.labels = labels;
    }

    public List<?> getAssignees() {
        return assignees;
    }

    public void setAssignees(List<?> assignees) {
        this.assignees = assignees;
    }

    public static class UserBean {
        private String login;
        private int id;
        private String avatar_url;
        private String gravatar_id;
        private String url;
        private String html_url;
        private String followers_url;
        private String following_url;
        private String gists_url;
        private String starred_url;
        private String subscriptions_url;
        private String organizations_url;
        private String repos_url;
        private String events_url;
        private String received_events_url;
        private String type;
        private boolean site_admin;

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAvatar_url() {
            return avatar_url;
        }

        public void setAvatar_url(String avatar_url) {
            this.avatar_url = avatar_url;
        }

        public String getGravatar_id() {
            return gravatar_id;
        }

        public void setGravatar_id(String gravatar_id) {
            this.gravatar_id = gravatar_id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getHtml_url() {
            return html_url;
        }

        public void setHtml_url(String html_url) {
            this.html_url = html_url;
        }

        public String getFollowers_url() {
            return followers_url;
        }

        public void setFollowers_url(String followers_url) {
            this.followers_url = followers_url;
        }

        public String getFollowing_url() {
            return following_url;
        }

        public void setFollowing_url(String following_url) {
            this.following_url = following_url;
        }

        public String getGists_url() {
            return gists_url;
        }

        public void setGists_url(String gists_url) {
            this.gists_url = gists_url;
        }

        public String getStarred_url() {
            return starred_url;
        }

        public void setStarred_url(String starred_url) {
            this.starred_url = starred_url;
        }

        public String getSubscriptions_url() {
            return subscriptions_url;
        }

        public void setSubscriptions_url(String subscriptions_url) {
            this.subscriptions_url = subscriptions_url;
        }

        public String getOrganizations_url() {
            return organizations_url;
        }

        public void setOrganizations_url(String organizations_url) {
            this.organizations_url = organizations_url;
        }

        public String getRepos_url() {
            return repos_url;
        }

        public void setRepos_url(String repos_url) {
            this.repos_url = repos_url;
        }

        public String getEvents_url() {
            return events_url;
        }

        public void setEvents_url(String events_url) {
            this.events_url = events_url;
        }

        public String getReceived_events_url() {
            return received_events_url;
        }

        public void setReceived_events_url(String received_events_url) {
            this.received_events_url = received_events_url;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public boolean isSite_admin() {
            return site_admin;
        }

        public void setSite_admin(boolean site_admin) {
            this.site_admin = site_admin;
        }
    }
}
