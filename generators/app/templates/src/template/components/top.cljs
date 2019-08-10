(ns template.components.top
  (:require [re-frame.core :refer [subscribe dispatch]]
            [reagent.core :as r]
            ["@material-ui/core/AppBar" :default AppBar]
            ["@material-ui/core/Toolbar" :default Toolbar]
            ["@material-ui/core/IconButton" :default IconButton]
            ["@material-ui/icons/Menu" :default MenuIcon]
            ["@material-ui/core/Button" :default Button]
            ["@material-ui/core/Menu" :default Menu]
            ["@material-ui/core/List" :default List]
            ["@material-ui/core/Avatar" :default Avatar]
            ["@material-ui/core/ListItem" :default ListItem]
            [template.fb.auth :refer [sign-in-with-google sign-out]]))

(defn login []
  (let []
    (fn []
      [:div
       [:> Button {:color "inherit" :on-click #(sign-in-with-google)} "Zaloguj"]])))

(defn logged-in-user-pane [user]
  (let []
    (fn []
      [:div.right-pane
       [:> IconButton {:id "avatar-button" :on-click #(sign-out)}
        [:> Avatar {:src (:photo @user) :alt (:name @user)}]]])))

(defn bar []
  (let [user (subscribe [:user])]
    (fn []
      [:div
       [:> AppBar {:position "fixed" :class "top-bar"}
        [:> Toolbar {:style {:min-height 64} :color "primary"}
         [:> IconButton
          [:> MenuIcon {:style {:color "white"}}]]
         (if (empty? @user)
           [login]
           [logged-in-user-pane user])]]])))