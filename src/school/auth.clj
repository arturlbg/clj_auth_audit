(ns school.auth
  (:require [io.pedestal.interceptor :refer [interceptor]]
            [buddy.auth :refer [authenticated?]]
            [buddy.auth.backends.token :refer [jws-backend]]))

(def auth-backend (jws-backend {:secret ""}))

(def auth-interceptor
  (interceptor
    {:name ::auth-interceptor
     :enter (fn [context]
              (if (authenticated? context)
                context
                (assoc context :response {:status 401 :body "Access not authorized"})))}))
