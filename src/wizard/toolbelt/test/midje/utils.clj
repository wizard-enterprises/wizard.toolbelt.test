(ns wizard.toolbelt.test.midje.utils
  (:use fudje.sweet tupelo.core))

(defn- assert-ex-info-message
  [message]
  (fn [ex]
    (= message (.getMessage ex))))

(defn- assert-ex-info-data
  [data]
  (fn [ex]
    (submatch? data (ex-data ex))))

(defn- assert-ex-info
  ([message-or-data]
   (if (string? message-or-data)
     (assert-ex-info-message message-or-data)
     (assert-ex-info-data message-or-data)))
  ([message data]
   (every-pred
    (assert-ex-info-message message)
    (assert-ex-info-data data))))

(defn throws-ex-info
  [& args]
  (throws clojure.lang.ExceptionInfo (apply assert-ex-info args)))
