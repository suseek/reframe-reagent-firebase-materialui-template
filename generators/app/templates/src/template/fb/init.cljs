(ns template.fb.init
  (:require ["firebase" :as firebase]
            [template.fb.auth :as fb-auth]
            [oops.core :refer [ocall oget]]
            [template.config]))

(defn firebase-init
  []
  (let []
    (firebase/initializeApp (clj->js template.config/firebase))
    (fb-auth/on-auth-state-changed!)))