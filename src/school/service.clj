(ns school.service
  (:require [io.pedestal.http :as http]
            [io.pedestal.http.route :as route]
            [escola-backend.handlers :as handlers]))

(def common-interceptors
  [(http/json-body)])
   ;; Outros interceptors comuns


(def routes
  #{["/login" :post (conj common-interceptors `handlers/login)]
    ["/students" {:get (conj common-interceptors `handlers/listar-students)
                  :post (conj common-interceptors `handlers/criar-aluno)}]
    ["/students/:id" {:get (conj common-interceptors `handlers/obter-aluno)
                      :put (conj common-interceptors `handlers/atualizar-aluno)
                      :delete (conj common-interceptors `handlers/deletar-aluno)}]})
