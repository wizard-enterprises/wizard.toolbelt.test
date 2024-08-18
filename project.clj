(defproject wizard.toolbelt.test "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "https://github.com/wizard-enterprises/wizard.toolbelt.test"
  :min-lein-version "2.0.0"

  :plugins [[lein-git-deps "0.0.2"]]

  :git-dependencies []
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [org.clojure/clojurescript "1.11.132"]
                 [midje "1.10.10"]
                 [potemkin "0.4.6"]]

  :profiles {:dev  {:plugins      [[lein-midje "3.2.1"]
                                   [refactor-nrepl "3.9.1"]
                                   [cider/cider-nrepl "0.49.1"]]}}

  :repl-options {:init (use 'midje.repl)})
