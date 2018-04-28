/**
 * Created by raingsey on 9/3/2015.
 */
/*
 Jasmine java script
 
 * describe("Hello World",function(){        //it's called a suite
    it("says hello",function(){             //it's called a spec
        expect(helloWorld()).toEqual("Hello World");    //it's called a matcher 
    });
    it("contain word ", function(){
        expect(helloWorld()).toContain("World");
    });
});*/

/* jasmine-node */
require("../../src/example/Hello.js");
describe("Hello World Test",function(){
	it("say hello world",function(){
		expect(sayHello()).toEqual("Hello World");
	});
});