(ns store.adapters.handlers
  (:require [ring.util.response :as response]
            [buddy.sign.jwt :as jwt]
            [store.db.query :as db]
            [ring.util.response :as response]))

(def secret "")

(defn hello-handler [request]
  {:status 200 :body "Hello, World!"})

(def routes
  #{["/hello" :get hello-handler :route-name :hello]})

(defn login [request]
  (let [{:keys [email password]} (:json-params request)
        user (db/get-user-by-email email)]
    (if (and user (= password (:password user)))
      (let [token (jwt/sign {:user-id (:id user)} secret)]
        (response/response {:token token}))
      (response/bad-request "User or password incorrect"))))

(defn list-students []
  (db/list-students))

(defn create-student [student]
  (db/list-students)) ;;fix it