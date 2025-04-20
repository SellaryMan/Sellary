'use client'
import Input from "../common/Input";
import { useState } from "react";
const comOptions =  [
  {
    id :"회사명",
    value : "회사명"
  }
  ,
  {
    id : "넥슨",
    value : "넥슨"
  },
  {
    id : "넷마블",
    value : "넷마블"
  },
  {
    id : "펄어비스",
    value : "펄어비스"
  }
]
const SearchBox = () => {
    const [code, setCode] = useState("")
    const [com, setCom] = useState(comOptions[0].value)

    const onChangeCode =(e: React.ChangeEvent<HTMLInputElement>)=>{
      setCode(e.target.value)
      console.log(code)
    }
    const onChangeCom =(e: React.ChangeEvent<HTMLSelectElement>)=>{
      setCom(e.target.value)
      console.log(code)
    }

    // 모든 폼 요소에 적용할 공통 클래스
    const formElementClass = "flex border border-gray-300 w-full bg-white text-[0.6rem] px-1";
    return (
      <form className="grid grid-cols-9 gap-1 p-2 w-full max-w-full bg-gray-200">
        <Input.Select
          className={formElementClass}
          options = {comOptions}
          selectedValue={com}
          onSelectChange={(e)=>onChangeCom(e)}
        />
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