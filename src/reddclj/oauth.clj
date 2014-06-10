(ns reddclj.oauth
  (:require [clojure.java.browse :as browse]
            [ring.util.codec :as url-util]
            [reddclj.server :as server]))

; random string generator
(def alphanumeric "ABCDEFGHIJKLMNOPQRSTUVWXYZ")
(defn- rand-str [length]
    (apply str (repeatedly length #(rand-nth alphanumeric))))

(defn- auth-web-url [client-id resp-type state uri duration scope]
  (str "https://ssl.reddit.com/api/v1/authorize?client_id="
       client-id 
       "&response_type="
       resp-type
       "&state="
       state  
       "&redirect_uri="
       uri
       "&duration="
       duration 
       "&scope="
       scope))

(defn auth-web [client-id resp-type state uri duration scope]
  (do
    ;(.start (Thread. server/run-server))
    (browse/browse-url 
      (auth-web-url client-id resp-type state uri duration scope))))

(auth-web "bgHdUgiJvoIIhw" "code" (rand-str 10) 
          (url-util/url-encode "http://127.0.0.1:59344/")
          "permanent"
          (str "modposts,identity,edit,flair,history,"
               "modconfig,modflair,modlog,modposts,modwiki,"
               "mysubreddits,privatemessages,read,report,save,"
               "submit,subscribe,vote,wikiedit,wikiread"))
