(ns template.core
  (:require
   [template.db.handlers]
   [template.db.subs]
   [reagent.core :as r]
   [reagent.session :as session]
   [re-frame.core :refer [subscribe dispatch dispatch-sync]]
   [template.fb.init :refer [firebase-init]]
   ["@material-ui/core/Fade" :default Fade]
   ["@material-ui/core/styles" :refer [MuiThemeProvider createMuiTheme]]
   ["@material-ui/core/List" :default List]
   ["@material-ui/core/colors/cyan" :default cyanColor]
   [template.components.top :as top]
   [oops.core :refer [ocall oget oset!]]))

(def theme (createMuiTheme (clj->js {:palette {:primary {:light (oget cyanColor "400")
                                                         :main (oget cyanColor "600")
                                                         :dark (oget cyanColor "900")
                                                         :contrastText "#fff"}}})))

(defn main-component []
  (let []
    (fn []
      [:> Fade {:in true}
       [:div
        [top/bar]]])))

(defn app []
  (let [user (subscribe [:user])]
    (fn []
      [:> MuiThemeProvider
       {:theme theme}
       (if @user
         [main-component])])))

(defn render []
  (r/render [app]
            (js/document.getElementById "app")))

(defn on-window-resize []
  (dispatch [:window-width (oget js/window "innerWidth")]))

(defn ^:export init []
  (dispatch-sync [:initialize])
  (firebase-init)
  (render)
  (ocall js/window "addEventListener" "resize" on-window-resize))