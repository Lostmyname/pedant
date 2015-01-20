(map inc [1 2 3])

(str "Hello" " " "You")

(ns localshop.handler
  (:use compojure.core)
  (:require [localshop.routes.api.items :as routes-api-items]
            [ring.middleware.json :as middleware]
            [compojure.handler :as handler]
            [compojure.route :as route]))

;; map the route handlers
(defroutes app-routes
  (context "/api/item" [] routes-api-items/routes))

;; define the ring application
(def app
  (-> (handler/api app-routes)
      (middleware/wrap-json-body)
      (middleware/wrap-json-params)
      (middleware/wrap-json-response)))
