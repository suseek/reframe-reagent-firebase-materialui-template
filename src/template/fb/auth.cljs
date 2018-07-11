(ns template.fb.auth
  (:require ["firebase" :as firebase]
            [oops.core :refer [ocall oget]]
            [re-frame.core :refer [dispatch]]))

(defn sign-in-with-google
  []
  (let [provider (firebase/auth.GoogleAuthProvider.)]
    (ocall (firebase/auth) "signInWithPopup" provider)))

(defn sign-out
  []
  (ocall (firebase/auth) "signOut")
  (dispatch [:sign-out]))

(defn get-user-details [auth-object]
  (let [name (oget auth-object "displayName")
        photo (oget auth-object "photoURL")
        email (oget auth-object "email")
        uid (oget auth-object "uid")]
    {:name name
     :photo photo
     :email email
     :uid uid}))

(defn on-auth-state-changed!
  []
  (ocall
   (firebase/auth)
   "onAuthStateChanged"
   (fn
     [user]
     (if user
       (dispatch [:update-user (get-user-details user)])
       (dispatch [:set-anonymous-user])))))