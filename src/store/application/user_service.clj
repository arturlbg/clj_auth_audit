(ns store.application.user-service
  (:require [io.pedestal.http :as http]
            [io.pedestal.http.route :as route]
            [store.handlers :as handlers]
            [store.audit :as audit]
            [store.auth :as auth]))

(def common-interceptors
  [(http/json-body)
   auth/auth-interceptor
   audit/audit-interceptor])
   ;; Outros interceptors


(def routes
  #{["/login" :post (conj common-interceptors `handlers/login)]
    ["/students" {:get (conj common-interceptors `handlers/list-students)
                  :post (conj common-interceptors `handlers/create-student)}]
    #_#["/students/:id" {:get (conj common-interceptors `handlers/obter-aluno)
                         :put (conj common-interceptors `handlers/atualizar-aluno)
                         :delete (conj common-interceptors `handlers/deletar-aluno)}]}) ;;finish
