(ns reddclj.server
  (:require [ring.adapter.jetty :as jetty]))

(def modhash (ref ""))
(def hashcode (ref ""))

(defn- store-hash [request]
  (dosync 
    (ref-set modhash "")
    (ref-set hashcode "")))

(defn- handler [request]
  (let []
    (store-hash (:query-string request))
    {:status 200
     :headers {"Content-Type" "text/html"}
     :body (:query-string request)}))

(defn run-server[]
  (jetty/run-jetty handler {:port 59344}))
