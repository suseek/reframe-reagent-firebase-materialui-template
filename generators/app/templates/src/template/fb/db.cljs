(ns template.fb.db
  (:require
   ["firebase" :refer [database]]
   [oops.core :refer [ocall]]))

(defn db-ref [path]
  (.ref (database) path))

(defn save! [path value]
  (ocall (db-ref path) "set" value))

(defn on-added! [path action]
  (ocall (db-ref path) "on" "child_added" action))

(defn on-value! [path action]
  (ocall (db-ref path) "on" "value" action))

(defn off-added! [path]
  (ocall (db-ref path) "off" "child_added"))