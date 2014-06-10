(ns reddclj.server
  (:require [ring.adapter.jetty :as jetty]))

(defn- handler [request]
  (do
    {:status 200
     :headers {"Content-Type" "text/html"}
     :body (:query-string request)}))

(defn run-server[]
  (jetty/run-jetty handler {:port 59344}))
