(ns pedant.core.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [compojure.handler :as handler]
            [ring.middleware.json :as middleware]
            [clj-http.client :as client]
            [environ.core :refer [env]]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults]]))

(defn send-checklist [pr-url]
  (let [checklist (slurp "checklist.md")]
    (client/post pr-url
       {:form-params {:body checklist}
        :headers {"Authorization" (str "Basic " (env :github-token))}
        :content-type :json })))


(defn create-pr-list [doc]
  (let [pr-url ((doc "pull_request") "comments_url")]
    (send-checklist pr-url)
    (println (str "Posted to " pr-url)))
  {:status 200})

(defroutes app-routes
  (POST "/pull-request" {body :body} (create-pr-list body))
  (route/not-found "Not Found"))

(def app
  (-> (handler/api app-routes)
      (middleware/wrap-json-body)
      (middleware/wrap-json-response)))
