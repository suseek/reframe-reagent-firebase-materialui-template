(ns template.fb.storage
  (:require
   ["firebase" :refer [storage]]
   [oops.core :refer [ocall]]))

(defn storage-ref [path]
  (.ref (storage) path))

(defn save! [path file metadata]
  (ocall (storage-ref path) "put" file metadata))