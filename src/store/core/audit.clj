(ns store.audit
  (:require [io.pedestal.interceptor :refer [interceptor]]
            [store.db :as db]
            [cheshire.core :as json]))

(def audit-interceptor
  (interceptor
    {:name ::audit-interceptor
     :leave (fn [context]
              (let [request (:request context)
                    response (:response context)
                    user-id (get-in request [:session :user-id])
                    method (name (:request-method request))
                    endpoint (:uri request)
                    request (json/encode (:json-params request))
                    response (json/encode (:body response))]
                (db/try-audit {:registered-user-id user-id
                               :http/method method
                               :endpoint endpoint
                               :request request
                               :response response}))
              context)}))
