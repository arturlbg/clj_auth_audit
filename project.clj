(defproject clj-auth-audit "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [io.pedestal/pedestal.service "0.5.8"]
                 [io.pedestal/pedestal.route "0.5.8"]
                 [io.pedestal/pedestal.jetty "0.5.8"]
                 [ring/ring-core "1.9.4"]
                 [ring/ring-json "0.5.0"]
                 [cheshire "5.10.0"]
                 [org.clojure/java.jdbc "0.7.12"]
                 [org.postgresql/postgresql "42.2.23"]
                 [buddy/buddy-auth "3.0.0"]]


  :repl-options {:init-ns clj-auth-audit.core})
