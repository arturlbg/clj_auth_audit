(ns store.db.query
  (:require [clojure.java.jdbc :as jdbc]
            [clojure.edn :as edn]))

(def config (edn/read-string (slurp "src\\store\\config\\config.edn")))

(def db-spec {:dbtype "postgresql"
              :dbname "store"
              :user (:database-user config)
              :password (:database-password config)})

(defn get-user-by-email [email]
  (first (jdbc/query db-spec
                     ["SELECT * FROM registered_user WHERE email = ?" email])))

(defn list-categories []
  (jdbc/query db-spec ["SELECT * FROM categories"]))

(defn try-audit [data]
  (jdbc/insert! db-spec :audit data))
