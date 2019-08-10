(ns <%= name %>.fb.init
  (:require ["firebase" :as firebase]
            [<%= name %>.fb.auth :as fb-auth]
            [oops.core :refer [ocall oget]]
            [<%= name %>.config]))

(defn firebase-init
  []
  (let []
    (firebase/initializeApp (clj->js <%= name %>.config/firebase))
    (fb-auth/on-auth-state-changed!)))
