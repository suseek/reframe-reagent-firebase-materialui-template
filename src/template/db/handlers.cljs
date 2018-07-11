(ns template.db.handlers
  (:require [re-frame.core :as rf]
            [oops.core :refer [ocall oget]]
            [template.fb.db :as firebase-db]
            [re-frame.core :refer [dispatch]]
            ["uuid" :as random]))

(def empty-db
  {:window-width (oget js/window "innerWidth") 
   :user {}})


(rf/reg-event-db
 :update-user
 (fn
   [db [_ user]]
   (let [user-details {:name (:name user)
                       :email (:email user)
                       :photo (:photo user)}]
     (firebase-db/save! (str "users/" (:uid user)) (clj->js user-details))
     (assoc db :user user))))

(rf/reg-event-db
 :set-anonymous-user
 (fn
   [db _]
   (assoc db :user {})))

(rf/reg-event-db
 :initialize
 (fn
   [_ _]
   empty-db))

(rf/reg-event-db
 :sign-out
 (fn
   [_ _]
   empty-db))

(rf/reg-event-db
 :window-width
 (fn [db [_ window-width]]
   (assoc db :window-width window-width)))