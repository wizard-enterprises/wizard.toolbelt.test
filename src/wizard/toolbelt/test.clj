(ns wizard.toolbelt.test
  (:require wizard.toolbelt clojure.test)
  (:gen-class))

(wizard.toolbelt/intern-all-from *ns* 'clojure.test)
