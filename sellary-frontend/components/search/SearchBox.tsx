'use client'
import Input from "../common/Input";
import { useState } from "react";
import useSearch from "@/hooks/useSearch";
const SearchBox = () => {
    const [comName, setComName] = useState("")
    const onChangeComName = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
          setComName(e.target.value);
        }

    // 모든 폼 요소에 적용할 공통 클래스
    const formElementClass = "flex w-full bg-transparent text-[0.6rem] px-1";
    return (
      <form className="grid grid-cols-4 gap-1 p-2 w-full max-w-full bg-gray-200">
        <Input.Select
          className={formElementClass}
          onSelectChange={onChangeComName}
          selectedValue={comName}
          options={
            [
              {
                id: "회사명",
                value : ""
              },
              {
                id: "data1",
                value : "value1"
              },
              {
                id: "data2",
                value : "value2"
              },
              {
                id: "data3",
                value : "value3"
              }
            ]
          }
        />
        <Input.Select
          className={formElementClass}
          onSelectChange={onChangeComName}
          selectedValue={comName}
          options={
            [
              {
                id: "공급사",
                value : ""
              },
              {
                id: "data1",
                value : "value1"
              },
              {
                id: "data2",
                value : "value2"
              },
              {
                id: "data3",
                value : "value3"
              }
            ]
          }
        />
        <Input.Select
          className={formElementClass}
          onSelectChange={onChangeComName}
          selectedValue={comName}
          options={
            [
              {
                id: "구분",
                value : ""
              },
              {
                id: "data1",
                value : "value1"
              },
              {
                id: "data2",
                value : "value2"
              },
              {
                id: "data3",
                value : "value3"
              }
            ]
          }
        />
        <button className={`row-span-2 bg-white w-full h-full rounded-[6px] text-sm border-1 border-gray-400` }>검색</button>
        <Input.Text className={formElementClass} placeholder="상품코드" onChange={(e)=>{}}/>
        <Input.Text className={formElementClass} placeholder="바코드" onChange={(e)=>{}}/>
        <Input.Text className={`${formElementClass} col-span-2`} placeholder="상품명" onChange={(e)=>{}}/>

      </form>
    );
  };
  
  export default SearchBox;