(ns pbranes.sbx.page.webdevcreative
  (:require [helix.core :refer [defnc]]
            [helix.dom :as d]
            [pbranes.sbx.domutils :refer [query-selector set-background-image!]]))

(defnc WebDevCreative []
  (set-background-image! "url('/images/nav-logo.jpg')")
  (d/main "hello world"))

(comment
  (js/console.log (query-selector "body"))
  (js/console.log   (.. (query-selector "body") -style -backgroundImage))
  (set! (.. (query-selector "body") -style -backgroundImage) nil)
  (prn (query-selector "body")))
