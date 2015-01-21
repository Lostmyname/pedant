(defproject pedant "0.2.0"
  :description "Post a checklist to a pull request"
  :url "https://github.com/Lostmyname/pedant"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [compojure "1.3.1"]
                 [clj-http "1.0.1"]
                 [environ "1.0.0"]
                 [ring/ring-jetty-adapter "1.3.2"]
                 [ring/ring-json "0.3.1"]
                 [ring/ring-defaults "0.1.2"]]
  :plugins [[lein-ring "0.8.13"]]
  :ring {:handler pedant.web/app}
  :profiles {:dev {:dependencies [[javax.servlet/servlet-api "2.5"] [ring-mock "0.1.5"]]}})
