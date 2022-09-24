package main

import (
	"fmt"
	"math/rand"
	"time"
)

type Item int

const itemA = 0
const itemB = 1
const itemC = 2

func getTwoRandomItems() [2]Item {
	var res [2]Item
	n := rand.Intn(3)
	switch n {
	case 0:
		res = [2]Item{itemA, itemB}
	case 1:
		res = [2]Item{itemB, itemC}
	case 2:
		res = [2]Item{itemA, itemC}
	}
	return res
}

func smoker(item Item, dataCh chan [2]Item, callbackCh chan int) {
	for true {
		container := [3]bool{false, false, false}
		data := <-dataCh
		container[item] = true
		container[data[0]] = true
		container[data[1]] = true

		flag := true
		for i := 0; i < 3; i++ {
			if !container[i] {
				flag = false
				break
			}
		}

		if flag {
			fmt.Println("Person ", item, " is smoking...")
			time.Sleep(1 * time.Second)
			fmt.Println("Person ", item, " finished smoking")
			callbackCh <- 1
		} else {
			fmt.Println("Person ", item)
			dataCh <- data
		}
	}
}
func producer(dataCh chan [2]Item, callbackCh chan int) {
	for true {
		fmt.Println("Producer puts new item")
		dataCh <- getTwoRandomItems()
		<-callbackCh
	}
}

func main() {
	rand.Seed(time.Now().Unix())

	dataCh := make(chan [2]Item, 1)
	callbackCh := make(chan int, 1)

	go smoker(itemA, dataCh, callbackCh)
	go smoker(itemB, dataCh, callbackCh)
	go smoker(itemC, dataCh, callbackCh)
	go producer(dataCh, callbackCh)

	select {}
}
