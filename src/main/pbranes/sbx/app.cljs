(ns pbranes.sbx.app
  (:require [helix.core :refer [defnc $]]
            [helix.dom :as d]
            [react-router-dom :as rr]
            ["react-dom/client" :as rdom]
            [pbranes.sbx.layout :refer [Layout]]
            [pbranes.sbx.page.home :refer [Home]]
            [pbranes.sbx.page.webdevcreative :refer [WebDevCreative]]))

(defnc Router []
  ($ rr/Routes
     ($ rr/Route {:path "/" :element ($ Layout)}
        ($ rr/Route {:path "/" :element ($ Home)})
        ($ rr/Route {:path "/wdc" :element ($ WebDevCreative)}))))

(defnc app []
  ($ rr/BrowserRouter
     ($ Router)))

;; start your app with your favorite React renderer
(defonce root (rdom/createRoot (js/document.getElementById "root")))

(defn ^:dev/after-load init! []
  (.render root ($ app)))
