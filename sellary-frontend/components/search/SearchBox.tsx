'use client'
import Input from "../common/Input";
import { useState } from "react";
const SearchBox = () => {
    const [code, setCode] = useState("")
    const onChangeCode =(e: React.ChangeEvent<HTMLInputElement>)=>{
      setCode(e.target.value)
      console.log(code)
    }
    // 모든 폼 요소에 적용할 공통 클래스
    const formElementClass = "flex border border-gray-300 w-full bg-white text-[0.6rem] px-1";
    return (
      <form className="grid grid-cols-9 gap-1 p-2 w-full max-w-full bg-gray-200">
        <select className={formElementClass} defaultValue={"default"}>
          <option value ="default" >회사명</option>
          <option >1</option>
          <option>2</option>
          <option>3</option>
        </select>
        <select className={formElementClass}>
        <option value ="default" >공급사</option>
          <option>1</option>
          <option>2</option>
          <option>3</option>
        </select>
        <select className={formElementClass}>
         <option value ="default" >구분</option> 
          <option>1</option>
          <option>2</option>
          <option>3</option>
        </select>
        <Input.Text className={formElementClass} placeholder="상품코드" onChange={(e)=>onChangeCode(e)}/>
        <input className={formElementClass} placeholder="바코드"/>
        <select className={formElementClass}>
         <option value ="default" >매입가격</option>
          <option>1</option>
          <option>2</option>
          <option>3</option>
        </select>
        <input className={`${formElementClass}`} />
        <input className={`${formElementClass}`} />
        <button className={`${formElementClass} row-span-2` }>검색</button>
        <input className={`${formElementClass} col-span-2` } placeholder="상품명"/>
        <select className={formElementClass}>
          <option value ="default" >관리자키워드</option> 
          <option>1</option>
          <option>2</option>
          <option>3</option>
        </select>
        <input className={`${formElementClass}`} />
        <select className={formElementClass}>
          <option value ="default" >유통기한</option> 
          <option>1</option>
          <option>2</option>
          <option>3</option>
        </select>
        <select className={formElementClass}>
         <option value ="default" >총 재고</option>
          <option>1</option>
          <option>2</option>
          <option>3</option>
        </select>
        <input className={`${formElementClass}`} />
        <input className={`${formElementClass}`} />

      </form>
    );
  };
  
  export default SearchBox;