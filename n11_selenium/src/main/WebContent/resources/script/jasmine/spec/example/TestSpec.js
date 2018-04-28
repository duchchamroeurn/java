/**
 * Created by raingsey on 9/3/2015.
 */
describe("Test Suite Calculation",function(){
    it("Sum of a+b",function(){
        expect(sum(5,6)).toEqual(11);
    });
    it("Subtract of a-b",function(){
        expect(subtract(6,3)).toEqual(3);
    });
    it("Multiply of a*b",function(){
        expect(multiply(6,3)).toEqual(18);
    });
    it("Divide of a/b",function(){
        expect(divide(6,3)).toEqual(2);
    });
});