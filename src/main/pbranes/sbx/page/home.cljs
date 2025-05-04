(ns pbranes.sbx.page.home
  (:require [helix.core :refer [defnc $]]
            [helix.hooks :as hooks]
            [helix.dom :as d]
            [pbranes.sbx.domutils :refer [set-background-image!]]))

(defn init []
  (js/console.log "call to init function")
  (set-background-image! "url('/images/nav-logo.jpg')"))

(defnc Home []
  (hooks/use-effect
    :once
    (init))

  (let [[state set-state] (hooks/use-state {:name ""})]

    (d/div
     
      )))
