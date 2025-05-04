(ns pbranes.sbx.domutils)

(defn query-selector [query]
  (js/document.querySelector query))


(defn set-background-image! [image]
    (set! (.. (query-selector "body") -style -backgroundImage) image))
