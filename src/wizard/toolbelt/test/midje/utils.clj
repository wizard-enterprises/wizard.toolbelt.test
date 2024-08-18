(ns wizard.toolbelt.test.midje.utils
  (:use midje.sweet tupelo.core)
  (:require [potemkin :refer [unify-gensyms]]))

(defmacro thread-list
  [thread x & exprs]
  (unify-gensyms
   `(let [x## ~x]
      (list ~@(map (fn [e] `(~thread x## ~e)) exprs)))))

(defmacro list->>
  [x & exprs]
  `(thread-list ->> ~x ~@exprs))

(defmacro list->
  [x & exprs]
  `(thread-list -> ~x ~@exprs))

(defmacro thread-context-list
  [thread x & exprs]
  `(context (str '~x)
     (thread-list ~thread ~x ~@exprs)))

(defmacro context->>
  [x & exprs]
  `(thread-context-list ->> ~x ~@exprs))

(defmacro context->
  [x & exprs]
  `(thread-context-list -> ~x ~@exprs))

(defmacro thread-it-list
  [thread x message & exprs]
  `(it (str '~x " " '~message)
     (thread-list ~thread ~x ~@exprs)))

(defmacro it->>
  [x message & exprs]
  `(thread-it-list ->> ~x ~message ~@exprs))

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
