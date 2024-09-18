(ns wizard.toolbelt.test
  (:use wizard.toolbelt)
  (:require clojure.test wizard.toolbelt.test.matchers matcher-combinators.test)
  (:gen-class))

(intern-all-from *ns* 'clojure.test)

;; hacked together liberally from source
;; https://github.com/clojure/clojure/blob/master/src/clj/clojure/test.clj#L504
(defmethod assert-expr 'thrown-ex-info? [msg form]
  ;; (is (thrown-with-msg? c re expr))
  ;; Asserts that evaluating expr throws an exception of class c.
  ;; Also asserts that the message string of the exception matches
  ;; (with re-find) the regular expression re.
  (let [re   (nth form 1)
        re   (cond-> re (string? re) re-pattern)
        data (nth form 2)
        body (nthnext form 3)]
    `(try ~@body
          (do-report {:type :fail, :message ~msg, :expected '~form, :actual nil})
          (catch Exception e#
            (if-not (= clojure.lang.ExceptionInfo (type e#))
              (do-report {:type     :fail,  :message ~msg,
                          :expected '~form, :actual  e#})
              (let [m# (ex-message e#)]
                (if-not (re-find ~re m#)
                  (do-report {:type     :fail,  :message ~msg,
                              :expected '~form, :actual  e#})
                  (let [d# (ex-data e#)]
                    (if-not (submap? ~data d#)
                      (do-report {:type     :fail,  :message ~msg,
                                  :expected '~form, :actual  e#})
                      (do-report {:type     :pass,  :message ~msg,
                                  :expected '~form, :actual  e#}))))))
            e#))))
