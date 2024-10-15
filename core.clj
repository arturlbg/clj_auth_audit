(ns core
  (:require [io.pedestal.http :as http]
            [store.adapters.handlers :refer [routes]]))

(def service
  {:env :prod
   ::http/routes routes
   ::http/type :jetty
   ::http/port 8080})

(defn start-server []
  (http/start (http/create-server service)))

(defn -main
  "Start the server."
  [& args]
  (start-server))
