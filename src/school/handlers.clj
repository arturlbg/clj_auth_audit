(ns school.handlers
  (:require [ring.util.response :as response]
            [buddy.sign.jwt :as jwt]
            [school.db :as db]
            [ring.util.response :as response]))

(def secret "")

(defn login [request]
  (let [{:keys [email password]} (:json-params request)
        user (db/get-user-by-email email)]
    (if (and user (= password (:password user)))
      (let [token (jwt/sign {:user-id (:id user)} secret)]
        (response/response {:token token}))
      (response/bad-request "User or password incorrect"))))