(ns wizard.toolbelt.test.midje
  (:require midje.sweet wizard.toolbelt.test.midje.utils wizard.toolbelt))

(wizard.toolbelt/intern-all-from *ns* 'midje.sweet)
(wizard.toolbelt/intern-all-from *ns* 'wizard.toolbelt.test.midje.utils)
