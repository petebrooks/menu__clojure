(ns menu.core-spec
  (:require [speclj.core :refer :all]
            [menu.core :refer :all]))

(describe "With the test menue"
  (it "Returns the correct solution"
    (let [expected_result]
      [{"mixed fruit" 1
        "hot_wings" 2
        "sampler_plate" 1}
       {"mixed_fruit" 7}]
    (should_contain expected_result (solve_menu "test_menus/easy_menu.txt")))))
