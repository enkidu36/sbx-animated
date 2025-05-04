(ns pbranes.sbx.layout
  (:require [helix.core :refer [defnc fnc $ <>]]
            [helix.dom :as d]
            [helix.hooks :as hooks]
            [pbranes.sbx.icons :refer [arrow-back-icon chevron-right-icon down-arrow-icon close-icon menu-icon settings-icon]]
            [pbranes.sbx.domutils :refer [query-selector]]
            [react-router-dom :refer [Outlet]]
            [react-transition-group :refer [CSSTransition]]))

(defnc DropdownMenu []
  (let [[active-menu set-active-menu] (hooks/use-state "main")
        [menu-height set-menu-height] (hooks/use-state nil)

        calc-height (fn [el]
                       (set-menu-height (.-offsetHeight el)))

        DropdownItem  (fnc [{:keys [left-icon right-icon go-to-menu] :as props}]
                        (d/a {:class "menu-item" :href "#" :on-click #(when (not (nil? go-to-menu)) (set-active-menu go-to-menu))}
                             (d/span {:class "icon-button"} left-icon)
                             (:children props)

                             (d/span {:class "icon-right"} right-icon)))]

    (d/div {:class "dropdown" :style {:height menu-height}}
           ($ CSSTransition
              {:classNames "menu-primary"
               :in (= "main" active-menu)
               :unmountOnExit true
               :timeout 500
               :onEnter #(calc-height %)}
              
              (d/div {:class "menu"}
                ($ DropdownItem "My Profile")
                ($ DropdownItem {:left-icon ($ settings-icon) :right-icon ($ chevron-right-icon) :go-to-menu "settings"} "Settings")))

           ($ CSSTransition
              {:classNames "menu-secondary"
               :in (= "settings" active-menu)
               :unmountOnExit true
               :timeout 500
               :onEnter #(calc-height %)}
              
              (d/div {:class "menu"}
                ($ DropdownItem {:left-icon ($ arrow-back-icon) :go-to-menu "main"})
                ($ DropdownItem "Settings")
                ($ DropdownItem "Settings")
                ($ DropdownItem "Settings")
                ($ DropdownItem "Settings")
                ($ DropdownItem "Settings")
                ($ DropdownItem "Settings")
                ($ DropdownItem "Settings"))))))

(defnc NavItem [{:keys [icon children]}]
  (let [[open set-open] (hooks/use-state false)]
    (d/li {:class "nav-item"}
          (d/a {:class "icon-button" :on-click #(set-open (not open))} icon)

          (when (and open children)
            children))))

(defnc Navbar [{:keys [children]}]
  (d/nav {:class "navbar"}
         (d/ul {:class "navbar-nav"} children)))

(defnc Layout []
  (<>
   ($ Navbar
      ($ NavItem {:icon ":)"})
      ($ NavItem {:icon ($ down-arrow-icon)}
         ($ DropdownMenu))
      ($ NavItem {:icon ($ close-icon)}))
   (d/main
    ($ Outlet))
   (d/footer)))

