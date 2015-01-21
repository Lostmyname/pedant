(ns pedant.web
  (:gen-class)
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [compojure.handler :refer [site]]
            [compojure.handler :as handler]
            [ring.middleware.json :as middleware]
            [clj-http.client :as client]
            [environ.core :refer [env]]
            [ring.adapter.jetty :as jetty]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults]]))

(defn send-checklist [pr-url]
  (let [checklist (slurp "checklist.md")]
    (client/post pr-url
       {:form-params {:body checklist}
        :oauth-token (env :github-token)
        :content-type :json })))


(defn create-pr-list [doc]
  (let [pr-url ((doc "pull_request") "comments_url")]
    (send-checklist pr-url)
    (println (str "Posted to " pr-url)))
  {:status 200})

(defroutes app-routes
  (GET "/" [] {:status 200 :body "Solid"})
  (POST "/pull-request" {body :body} (create-pr-list body))
  (route/not-found "Not Found"))

(def app
  (-> (handler/api app-routes)
      (middleware/wrap-json-body)
      (middleware/wrap-json-response)))

(defn -main [& [port]]
  (let [port (Integer. (or port (env :port) 5000))]
    (jetty/run-jetty (site #'app) {:port port :join? false})))
