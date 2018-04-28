/**
 * Created by raingsey on 9/3/2015.
 */


/*  http://jasmine.github.io/edge/introduction.html */


/* It uses === to perform this comparison */
describe("The toBe/toEqual Matcher", function () {
    it("compare both types and values 'toBe'", function () {
        var actual = "123";
        var expected = "123";
        expect(actual).toBe(expected);
    });
    it("count array length length 'toBe'", function () {
        expect(ourDataArray.Questions.length).toBe(4);
    });
    it("count object properties 'toBe'", function () {
        expect(Object.keys(ourDataArray).length).toBe(2);
    });
    it("should be able to compare arrays 'toEqual'", function () {
        var actual = [1, 2, 3];
        var expected = [1, 2, 3];
        expect(actual).toEqual(expected);
    });
});
/* check if an object is defined/undefined */
describe("the toBeDefined/toBeUndefined Matcher", function () {
    /* (declared & initialized) */
    it("check defined objects' properties ", function () {
        var object = [1, 2, 3];
        expect(object).toBeDefined();
        /* = expect(object).not.toBeDefined(); */
    });
    /* (declared but not initialized yet)*/
    it("check undefined objects' properties ", function () {
        var object;
        expect(object).toBeUndefined();
    });
});
/* check null value */
describe("the toBeNull Matcher", function () {
    it("check null value of assignment", function () {
        var object = null;
        expect(object).toBeNull();
    });
    it("check not null value of assignment", function () {
        var object = 200;
        expect(object).not.toBeNull();
    });
});
/* check true/false of variable assignemnt value */
describe("the toBeTruthy/toBeFalsy", function () {
    it("check true of assignment", function () {
        var a = true;
        expect(a).toBeTruthy();
    });
    it("check false of assignment", function () {
        var b = false;
        expect(b).toBeFalsy();
    });
});

/* check string contain another string */
describe("the toContain Matcher", function () {
    it("check a string is found in another string", function () {
        expect("Hello World from Cairo").toContain("Cairo");
    });
    it("check if an Array contains a specific item", function () {
        expect(["TV", "Watch", "Table"]).toContain("Watch");
    });
});

/* check mathematical comparison */
describe("the toBeLessThan/toBeGreaterThan Matcher", function () {
    it("perform the less than operation", function () {
        expect(4).toBeLessThan(5);
    });
    it("perform the greater than operation", function () {
        expect(5).toBeGreaterThan(4);
    });
});
/* check value matching string or a regular expression.*/
describe("the toMatch Matcher", function () {
    it("should be able to match the value with a regular expression", function () {
        expect(5).toMatch("[0-9]");
    });
});

/* new in Jasmine version 2.3.4 */
describe("New in Jasmine 2.3.4-----------------", function () {
    it("The 'toBeCloseTo' matcher is for precision math comparison", function () {
        var pi = 3.1415926,
            e = 2.78;
        expect(pi).not.toBeCloseTo(e, 2);
        expect(pi).toBeCloseTo(e, 0);
    });
    it("The 'toThrow' matcher is for testing if a function throws an exception", function () {
        var foo = function () {
            return 1 + 2;
        };
        var bar = function () {
            return a + 1;
        };
        expect(foo).not.toThrow();
        expect(bar).toThrow();
    });
    it("The 'toThrowError' matcher is for testing a specific thrown exception", function () {
        var foo = function () {
            throw new TypeError("foo bar baz");
        };
        expect(foo).toThrowError("foo bar baz");
        expect(foo).toThrowError(/bar/);
        expect(foo).toThrowError(TypeError);
        expect(foo).toThrowError(TypeError, "foo bar baz");
    });
});

/* call back function */
describe("A spec using the fail function", function () {
    var failCallBack = function (x, callBack) {
        if (x) {
            callBack();
        }
    };
    it("should not call the callBack", function () {
        failCallBack(false, function () {
            fail("Callback has been called");
        });
    });
});


