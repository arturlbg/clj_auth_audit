(ns school.db
  (:require [clojure.java.jdbc :as jdbc]
            [clojure.edn :as edn]))

(def config (edn/read-string (slurp "src\\config.edn")))

(def db-spec {:dbtype "postgresql"
              :dbname "school"
              :user (:database-user config)
              :password (:database-password config)})

(defn get-user-by-email [email]
  (first (jdbc/query db-spec
                     ["SELECT * FROM registered_user WHERE email = ?" email])))

(defn list-students []
  (jdbc/query db-spec ["SELECT * FROM student"]))

(defn create-student [student]
  (jdbc/insert! db-spec :student student))
